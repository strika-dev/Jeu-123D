# Makefile ===

# Nom des fichiers sources
SOURCES := $(wildcard *.java)

# Nom de la classe principale pour exécuter (ex: JeuGUI ou JeuConsole)
MAIN := JeuGUI

# === Compilation ===
compile:
	javac $(SOURCES)

# === Exécution ===
run: compile
	java -Xmx16g $(MAIN)


# === Nettoyage ===
clean:
	rm -f *.class
	rm -f *~
	rm -f *.log
	rm -f *.jar
	rm -f *.form
# === Forcer re-compilation totale ===
rebuild: clean compile

.PHONY: compile run clean rebuild
