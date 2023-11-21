class ArchiveMenu(private val notesApp: NotesApp, private val archive: Archive) {

    fun showMenu() {
        while (true) {
            println("\nАрхив: ${archive.name}")
            println("1. Создать заметку")
            archive.notes.forEachIndexed { index, note -> println("${index + 2}. ${note.name}")
            }
            println("${archive.notes.size + 2}. Вернуться")

            val choice = notesApp.readInput("Выберите действие: ")

            when (choice) {
                null -> println("Ошибка: Вы должны ввести число.")
                1 -> notesApp.createNote(archive)
                in 2..archive.notes.size + 1 -> {
                    val selectedNote = archive.notes[choice - 2]
                    notesApp.viewNote(selectedNote)
                }
                archive.notes.size + 2 -> return else -> println("Ошибка: Выбранный пункт отсутствует.\n")
            }
        }
    }
}