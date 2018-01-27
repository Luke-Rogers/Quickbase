package com.quickbase.api

data class Column constructor(val name: String,
                              val type: String,
                              val nullable: Boolean,
                              val description: String? = null,
                              val characterLength: Int?,
                              val numericPrecision: Int?,
                              val numericScale: Int?)