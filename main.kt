package main

import java.io.File

fun readCOW(path: String): String{
    val file = File(path)
    val lines = file.readLines()
    return lines.joinToString("")
}

fun eval(source: String){
    val buffer = Array<Char> (200){ _ -> (0).toChar()}
    var ptr = 0 /* текущий указатель */
    var i = 0 //какой символ сейчас берем
    while (i < source.length){
        when (source[i].toString()) {
            "moO" -> ptr += 1
            "mOo" -> ptr -= 1
            "MoO" -> buffer[ptr] = buffer[ptr] + 1
            "MOo" -> buffer[ptr] = buffer[ptr] - 1
            "Moo" -> {
                if (buffer[ptr] == (0).toChar()) {
                    print(">>")
                    buffer[ptr] = readLine()?.toCharArray()?.get(0)!!
                }
                else {
                    print(buffer[ptr])
                }
            }
        }
        i += 1
    }
    print(ptr)
}

fun main(args: Array<String>){
    val source = readCOW("hello.cow")
    println(source)
    println("Done!")
    eval(source)
}