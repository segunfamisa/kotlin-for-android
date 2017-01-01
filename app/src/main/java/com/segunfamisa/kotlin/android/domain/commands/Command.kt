package com.segunfamisa.kotlin.android.domain.commands

interface Command<T> {
    fun execute(): T
}