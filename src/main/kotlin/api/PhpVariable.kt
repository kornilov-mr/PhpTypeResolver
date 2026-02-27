package org.example.api

class PhpVariable(private val name: String, private var docBlock: PhpDocBlock? = null) {

    fun getName(): String {
        return name
    }

    fun getDockBlock(): PhpDocBlock? {
        return docBlock
    }
}