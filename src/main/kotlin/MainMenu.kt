class MainMenu(private val notesApp: NotesApp) {

    fun showMenu() {
        while (true) {
            printMenu()
            val choice = notesApp.readInput("Выберите действие: ")

            when (choice) {
                null -> println("Ошибка: Вы должны ввести число.\n")
                1 -> notesApp.createArchive()
                in 2..notesApp.archives.size + 1 -> {
                    val selectedArchive = notesApp.archives[choice - 2]
                    ArchiveMenu(notesApp, selectedArchive).showMenu()
                }
                notesApp.archives.size + 2 -> notesApp.exit()
                else -> println("Ошибка: Выбранный пункт отсутствует.\n")
            }
        }
    }

    private fun printMenu() {
        println("Архивы:")
        println("1. Создать архив")
        notesApp.archives.forEachIndexed { index, archive ->
            println("${index + 2}. ${archive.name}")
        }
        println("${notesApp.archives.size + 2}. Выйти")
    }
}
