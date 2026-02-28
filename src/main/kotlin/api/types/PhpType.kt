package org.example.api.types


/**
 * Basic class for a php type with [UnionType] class as an inheritor
 * @property typeName the exact type name, for example, "int", "string", "mixed", "User"
 */
open class PhpType(private val typeName: String) {

    fun getTypeName(): String {
        return typeName
    }


    override fun toString(): String {
        return "PhpType(typeName='$typeName')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhpType

        return typeName == other.typeName
    }

    override fun hashCode(): Int {
        return typeName.hashCode()
    }

}