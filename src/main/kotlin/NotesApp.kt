import java.util.*

data class Note(val name: String, val content: String)

class Archive(val name: String) {
    val notes = mutableListOf<Note>()
}

class NotesApp {

    val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun run() {
        val mainMenu = MainMenu(this)
        mainMenu.showMenu()
    }

    fun createArchive() {
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

    fun createNote(archive: Archive) {
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

    fun viewNote(note: Note) {
        println("\nЗаметка: ${note.name}")
        println(note.content)
        println("1. Вернуться")
        val choice = readInput("Введите 1, чтобы вернуться: ")
        if (choice == 1) return else println("Ошибка: Выбраный пункт отсутствует.\n")
    }

    fun exit() {
        println("\nРабота завершена. До свидания!")
        System.exit(0)
    }

    fun readInput(prompt: String): Int? {
        print(prompt)
        val input = scanner.nextLine()
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}