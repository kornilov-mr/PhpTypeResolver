package org.example

import org.example.api.PhpVariable
import org.example.api.types.PhpType
import org.example.api.types.TypeFactory

class TypeInference {
    val typeFactory: TypeFactory = TypeFactory()
    fun inferTypeFromDoc(variable: PhpVariable): PhpType {
        if (variable.getDockBlock() == null)
            return PhpType("mixed")
        val docsBlockForVariable = variable.getDockBlock()!!.getTagsByName(variable.getName())
        if (docsBlockForVariable.isEmpty())
            return PhpType("mixed")
        val types = docsBlockForVariable.map { it.getTypeNameString() }
        val typesNames = types.flatMap { it.split("|") }
        if (typesNames.size == 1)
            return typeFactory.createType(typesNames[0])
        return typeFactory.createUnionType(typesNames)
    }
}