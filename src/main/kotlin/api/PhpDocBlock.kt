package org.example.api


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
            val tagName = docBlock.getTagName()
            tagsByName.getOrPut(tagName) { mutableListOf() }.add(docBlock)
        }
    }

    fun getTagsByName(tagName: String): List<DocBlock> {
        return docWithoutVariableNames + (tagsByName[tagName] ?: emptyList())
    }
}