package org.example.api.types

class TypeFactory {

    fun createType(typeName: String): PhpType {
        return PhpType(typeName)
    }

    fun createUnionType(tagList: List<String>): PhpType {
        return UnionType(tagList)
    }
}