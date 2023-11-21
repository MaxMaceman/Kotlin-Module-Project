class ArchiveMenu(private val notesApp: NotesApp, private val archive: Archive) {

    fun showMenu() {
        while (true) {
            println("\nАрхив: ${archive.name}")
            println("0. Создать заметку")
            archive.notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.name}")
            }
            println("${archive.notes.size + 1}. Вернуться")

            val choice = notesApp.readInput("Выберите действие: ")

            when {
                choice == null -> println("Ошибка: Вы должны ввести число.")
                choice == 0 -> notesApp.createNote(archive)
                choice in 1..archive.notes.size -> {
                    val selectedNote = archive.notes[choice - 1]
                    notesApp.viewNote(selectedNote)
                }
                choice == archive.notes.size + 1 -> return
                else -> println("Ошибка: Выбраный пункт отсутствует.\n")
            }
        }
    }
}