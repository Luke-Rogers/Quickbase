package com.quickbase.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "QUICKBASE")
class Quickbase(
        @EmbeddedId val key: QuickbaseKey,
        @Column(name = "COLUMN_TYPE") var columnType: String,
        @Column(name = "DESCRIPTION") var description: String? = null,
        @Column(name = "CHARACTER_LENGTH") var characterLength: Int? = null,
        @Column(name = "NUMERIC_PRECISION") var numericPrecision: Int? = null,
        @Column(name = "NUMERIC_SCALE") var numericScale: Int? = null,
        @Column(name = "NULLABLE") var nullable: Boolean = false
) : Serializable {

    @Embeddable
    class QuickbaseKey(
            @Column(name = "TABLE_NAME") var tableName: String,
            @Column(name = "COLUMN_NAME") var columnName: String
    ) : Serializable

}
