package org.example.api

/**
 * Parses and organizes PHP-style doc blocks from a given content string.
 *
 * @constructor
 * Initializes the `PhpDocBlock` instance by parsing all PHP-style doc blocks found within the given content string.
 * The parsed doc blocks are classified into two categories:
 * - Doc blocks associated with variable names: These are stored in a map categorized by their variable names.
 * - Doc blocks without variable names: These are stored in a separate list.
 *
 * @param content A string containing the PHP code or comments to parse for doc blocks. The doc blocks
 *                are expected to follow the standard PHP-style comment format (`*/
class PhpDocBlock(content: String) {
    private val tagsByName: MutableMap<String, MutableList<DocBlock>> = mutableMapOf()
    private val docWithoutVariableNames: MutableList<DocBlock> = mutableListOf()

    init {
        val regex = Regex("""/\*\*[\s\S]*?\*/""")
        val rawBlocks = regex.findAll(content)
        rawBlocks.forEach { matchResult ->
            val docBlock = DocBlock(matchResult.value)
            if (!docBlock.isValid)
                return@forEach
            if (!docBlock.hasVariableName) {
                docWithoutVariableNames.add(docBlock)
                return@forEach
            }
            val tagName = docBlock.getTagName()!!
            tagsByName.getOrPut(tagName) { mutableListOf() }.add(docBlock)
        }
    }

    fun getTagsByName(tagName: String): List<DocBlock> {
        return docWithoutVariableNames + (tagsByName[tagName] ?: emptyList())
    }
}