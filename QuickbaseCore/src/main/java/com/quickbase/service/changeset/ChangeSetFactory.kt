package com.quickbase.service.changeset

import liquibase.change.Change

interface ChangeSetFactory<in T> {
    fun generateChange(change: T): Change
}