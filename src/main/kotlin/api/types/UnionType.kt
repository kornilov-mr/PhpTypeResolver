package org.example.api.types


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