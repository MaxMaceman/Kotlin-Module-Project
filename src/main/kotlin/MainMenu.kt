class MainMenu(private val notesApp: NotesApp) {

    fun showMenu() {
        while (true) {
            printMenu()
            val choice = notesApp.readInput("Выберите действие: ")

            when {
                choice == null -> println("Ошибка: Вы должны ввести число.\n")
                choice == 0 -> notesApp.createArchive()
                choice in 1..notesApp.archives.size -> {
                    val selectedArchive = notesApp.archives[choice - 1]
                    ArchiveMenu(notesApp, selectedArchive).showMenu()
                }
                choice == notesApp.archives.size + 1 -> notesApp.exit()
                else -> println("Ошибка: Выбраный пункт отсутсвует.\n")
            }
        }
    }

    private fun printMenu() {
        println("Архивы:")
        println("0. Создать архив")
        notesApp.archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }
        println("${notesApp.archives.size + 1}. Выйти")
    }
}