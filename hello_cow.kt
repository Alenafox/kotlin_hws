//Done!
//Hello, World!

package main

import java.io.File

fun readCOW(path: String): MutableList<String> {
    var arr: MutableList<String> = mutableListOf()
    val file = File(path)
    for (line in file.readLines()){
        for(word in line.split(" "))
            if(word.isNotEmpty())
                arr.add(word)
    }
    return arr
}

fun getLoopBlocks(source: MutableList<String>): HashMap<Int, Int>{ //если цикл, то есть в сообщении скобки [.......]
    val blocks = HashMap<Int, Int>()
    val stack = mutableListOf<Int>()
    for ((i, char) in source.withIndex()){
        if (char == "MOO"){
            stack.add(i)
        }
        if (char == "moo"){
            blocks[i] = stack[stack.lastIndex]
            blocks[stack.removeAt(stack.lastIndex)] = i
        }
    }
    return blocks
}

fun eval(source: MutableList<String>){
    val buffer = Array<Char> (200){ _ -> (0).toChar()}
    var ptr = 0 /* текущий указатель */
    val blocks = getLoopBlocks(source)
    var i = 0 //какой символ сейчас берем
    while (i < source.size ){
        when (source[i].toString()) {
            "moO" ->ptr += 1
            "mOo" -> ptr -= 1
            "MoO" -> buffer[ptr] = buffer[ptr] + 1
            "MOo" -> buffer[ptr] = buffer[ptr] - 1
            "Moo" -> {
                if (buffer[ptr] == (0).toChar()) {
                    print(">>")
                    buffer[ptr] = readLine()?.toCharArray()?.get(0)!!
                } else {
                    print(buffer[ptr])
                }
            }
            "MOO" ->{
                if (buffer[ptr] == (0).toChar()){
                    i = blocks[i]!!
                }
            }
            "moo" ->{
                if (buffer[ptr] != (0).toChar()){
                    i = blocks[i]!!
                }
            }
        }
        i += 1
    }
}

fun main(args: Array<String>){
    val source = readCOW("hello.cow")
    println("Done!")
    eval(source)
}
