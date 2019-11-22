package datastructure

import java.lang.StringBuilder


/**
@author zzz
@date 2019/6/27 15:30
 **/

class BSTree {

    private var root: Node? = null

    private var maxLayerNumber = 0

    constructor(array: IntArray) {
        buildTree(array)
    }

    fun print() {
        val nodes = getNodesByLayer()
        nodes.forEach {
            val lineOne = StringBuilder()
            val lineTwo = StringBuilder()
            it.forEach { node ->
                if (node == null) {
                    lineOne.append("  ")
                    lineTwo.append("  ")
                } else {
                    if (it.indexOf(node) % 2 == 0) {
                        lineOne.append("|")
                    } else {
                        lineOne.append("\\")
                    }
                    lineOne.append(" ")
                    lineTwo.append(node.value)
                    lineTwo.append("  ")
                }
            }
            print(lineOne)
            println()
            print(lineTwo)
            println()
        }
    }

    private fun getNodesByLayer(): MutableList<List<Node?>> {
        val nodes: MutableList<List<Node?>> = mutableListOf()
        var currentLayer = 0
        while (currentLayer <= maxLayerNumber) {
            val layerData: MutableList<Node?> = mutableListOf()
            if (currentLayer == 0) {
                layerData.add(root)
            } else {
                val lastLayerData = nodes[currentLayer - 1]
                lastLayerData.forEach {
                    layerData.add(it?.left)
                    layerData.add(it?.right)
                }
            }
            nodes.add(layerData)
            currentLayer++
        }
        return nodes
    }

    private fun buildTree(array: IntArray?) {
        array?.run {
            if (array.isEmpty()) {
                return
            }
            val rootNode = Node(array[0])
            rootNode.layerNumber = 1
            root = rootNode
            root?.run {
                if (array.size > 1) {
                    for (i in 1 until array.size) {
                        addNode(this, array[i])
                    }
                }
            }

        }
    }

    private fun addNode(parent: Node, num: Int) {
        val temp = Node(num)
        if (num < parent.value) {
            if (parent.left != null) {
                addNode(parent.left!!, num)
            } else {
                parent.left = temp
                temp.layerNumber = parent.layerNumber + 1
            }
        } else {
            if (parent.right != null) {
                addNode(parent.right!!, num)
            } else {
                parent.right = temp
                temp.layerNumber = parent.layerNumber + 1
            }
        }
        if (temp.layerNumber > maxLayerNumber) {
            maxLayerNumber = temp.layerNumber
        }
    }

    inner class Node(var value: Int) {
        var left: Node? = null

        var right: Node? = null

        var layerNumber = 0
    }
}