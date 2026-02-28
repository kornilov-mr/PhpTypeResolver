package org.example.api.types

/**
 * Class for type creation based on type name
 */
class TypeFactory {

    /**
     * Creates a new instance of PhpType with the specified type name.
     *
     * @param typeName the name of the PHP type to create, such as "int", "string", or a custom class name.
     * @return a PhpType object representing the specified type.
     */
    fun createType(typeName: String): PhpType {
        return PhpType(typeName)
    }

    /**
     * Creates a new instance of UnionType with the specified type names.
     *
     * @param tagList the name of the PHP type to create, such as "int|string".
     * @return a PhpType object representing the specified types.
     */
    fun createUnionType(tagList: List<String>): PhpType {
        return UnionType(tagList)
    }
}