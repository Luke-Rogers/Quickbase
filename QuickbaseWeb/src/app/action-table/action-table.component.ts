import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-action-table',
  templateUrl: './action-table.component.html',
  styleUrls: ['./action-table.component.css']
})
export class ActionTableComponent implements OnInit {

  @Input() definition: Object;

  constructor() { }

  ngOnInit() {
  }

}
