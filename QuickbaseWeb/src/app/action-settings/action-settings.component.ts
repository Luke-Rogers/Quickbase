import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-action-settings',
  templateUrl: './action-settings.component.html',
  styleUrls: ['./action-settings.component.css']
})


export class ActionSettingsComponent implements OnInit {
  definition: Object;
  filteredActions: Observable<Object[]>;
  filteredTable: Observable<string[]>;
  settings: FormGroup;

  quickbase: any = {
    tables: [],
    actions: []
  };

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {
    this.createForm();
  }

  createForm() {
    this.settings = this.formBuilder.group({
      action: ['', Validators.required],
      table: ['', Validators.required,],
      context: ['calms_base', Validators.required],
      author: ['', Validators.required]
    })
  }

  ngOnInit() {
    this.http.get('/api/quickbase').subscribe(result => {
      this.quickbase = result;
      this.filteredTable = this.settings.get("table").valueChanges.startWith('').map(val => this.filter(val, this.quickbase.tables));
      this.filteredActions = this.settings.get("action").valueChanges.startWith('').map(val => this.filter(val, this.quickbase.actions));
    });
    this.settings.get("table").valueChanges.subscribe(val => this.getDefinition(val));
  }


  filter(val: any, filterList: any[]): string[] {
    return filterList.filter(option => {
      if (option.label) option = option.label;
      if (val.label) val = val.label;
      return option.toLowerCase().indexOf(val.toLowerCase()) === 0
    })
  }

  showLabel(val): string {
    return val.label;
  }

  getDefinition(tableName: string) {
    if (tableName && this.quickbase.tables.indexOf(tableName.trim()) >= 0) {
      this.http.get(`/api/quickbase/definition/${tableName}`).subscribe(result => {
        this.definition = result;
        console.log(result);
      })
    }
  }

}
