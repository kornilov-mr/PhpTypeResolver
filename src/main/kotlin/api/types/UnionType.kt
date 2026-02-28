package org.example.api.types

/**
 * Class Represents a PHP union type, created from a list of type names joined by a "|" separator.
 * Union types allow representing multiple possible types in a single definition.
 *
 * @param tagList A list of PHP type names (e.g., "int", "string", "mixed") that should be part of the union.
 *
 * This class inherits from [PhpType]
 */
class UnionType(tagList: List<String>) : PhpType(tagList.joinToString("|")) {
    override fun toString(): String {
        return "UnionType() from ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.getTypeName().hashCode()
    }

}