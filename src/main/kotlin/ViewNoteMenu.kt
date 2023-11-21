import java.util.Scanner

data class Note(val name: String, val content: String)

class Archive(val name: String) {

    val notes = mutableListOf<Note>()
}

class NotesApp {

    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun run() {

        while (true) {
            mainMenu()
            val choice = readInput("Выберите действие: ")

            when {
                choice == null -> println("Ошибка: Вы должны ввести число.\n")
                choice == 0 -> createArchive()
                choice in 1..archives.size -> {
                    val selectedArchive = archives[choice - 1]
                    archiveMenu(selectedArchive)
                }
                choice == archives.size + 1 -> exit()
                else -> println("Ошибка: Выбраный пункт отсутсвует.\n")
            }
        }
    }

    private fun mainMenu() {

        println("Архивы:")
        println("0. Создать архив")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }
        println("${archives.size + 1}. Выйти")
    }

    private fun createArchive() {

        print("\nВведите название архива: ")
        val archiveName = scanner.nextLine()
        if (archiveName.isNotBlank()) {
            val newArchive = Archive(archiveName)
            archives.add(newArchive)
            println("Архив '$archiveName' создан.\n")
        } else {
            println("Ошибка: Имя архива не может быть пустым.\n")
        }
    }

    private fun archiveMenu(archive: Archive) {

        while (true) {
            println("\nАрхив: ${archive.name}")
            println("0. Создать заметку")
            archive.notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.name}")
            }
            println("${archive.notes.size + 1}. Вернуться")

            val choice = readInput("Выберите действие: ")

            when {
                choice == null -> println("Ошибка: Вы должны ввести число.")
                choice == 0 -> createNote(archive)
                choice in 1..archive.notes.size -> {
                    val selectedNote = archive.notes[choice - 1]
                    viewNote(selectedNote)
                }
                choice == archive.notes.size + 1 -> return
                else -> println("Ошибка: Выбраный пункт отсутствует.\n")
            }
        }
    }

    private fun createNote(archive: Archive) {

        print("Название: ")
        val noteName = scanner.nextLine()
        print("Содержимое: ")
        val noteContent = scanner.nextLine()
        if (noteName.isNotBlank() && noteContent.isNotBlank()) {
            val newNote = Note(noteName, noteContent)
            archive.notes.add(newNote)
            println("Заметка '$noteName' добавлена в архив '${archive.name}'.")
        } else {
            println("Ошибка: Название и содержание не могут быть пустыми.\n")
        }
    }

    private fun viewNote(note: Note) {

        println("\nЗаметка: ${note.name}")
        println(note.content)
        println("1. Вернуться")
        val choice = readInput("Введите 1, чтобы вернуться: ")
        if (choice == 1) return
        else println("Ошибка: Выбраный пункт отсутствует.\n")
    }

    private fun exit() {

        println("\nРабота завершена. До свидания!")
        System.exit(0)
    }

    private fun readInput(prompt: String): Int? {

        print(prompt)
        val input = scanner.nextLine()
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}

fun main() {

    val app = NotesApp()
    app.run()
}
