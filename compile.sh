# Path: src/Main.java
echo "Preparing program..."
javac -d bin src/*.java

# Run the program with $1 fr java args
echo "Running program..."
java -cp bin Main $1
