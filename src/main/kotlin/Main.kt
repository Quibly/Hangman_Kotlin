import java.util.Scanner

// Class to hold guesses, phrases, and keep track of game progress
class Display {
    var maxGuesses = 7
    var missedLetters = 0
    var incorrectGuesses = 1
    var phrase: String = "The sum of aegis"
    var lettersGuessed = ArrayList<String>()
}

// Function to start the program and house control function.
fun main() {
    println("Welcome to Hangman!")
    println("")
    println("Phrases are generated automatically.")
    println("")
    control()
}

// Function that houses the progress of the program by controlling loops
// for gameplay and checks for game completion
fun control() {
    val display1 = Display()
    // While the player hasn't missed too many guesses or completed the puzzle.
    // they will be able to keep guessing
    while (display1.incorrectGuesses < display1.maxGuesses) {
        println("Please guess a letter:")
        val reader = Scanner(System.`in`)
        // Set 'x' as an input value for the game control
        var x = reader.next()
        x = x.lowercase()
        // When is used to determine if the guess is part of the phrase or not.
        // Returns a count of missed letters to Display.missedLetters variable
        when (x) {
            in display1.phrase.lowercase() -> {display1.missedLetters = correctGuess(display1, x)}
            !in display1.phrase.lowercase() -> {display1.missedLetters = incorrectGuess(display1, x)}
        }
        // If the player guessed all the letters, they win.
        // Otherwise, if they miss too many guesses they lose.
        if (display1.missedLetters == 0) {
            display1.incorrectGuesses = display1.maxGuesses
            println("You got all of the letters! You won!")
        } else if (display1.incorrectGuesses == display1.maxGuesses) {
            println("You hung the man. You lost at hang man. He's dead. Sorry.")
        }
    }
}

// Function to create the display of data for gameplay.
// It shows a display of the hangman graphic, guessed letters,
// number of missed guesses left, and determines missed guesses.
fun createDisplay(display: Display): Int {
    var counter = 0
    val displayArray = arrayOf("  |  ", "  () ", "  -  ", " /|\\ ", " --- ", " || ", " __ ")
    // Loop to display the status of the hangman graphic.
    for (i in 1..display.incorrectGuesses)
        println(displayArray[i-1])
    println("")
    println("")
    // Loop to display the guessed and missed letters of the puzzle.
    for (i in 1..display.phrase.length) {
        val letter: String = display.phrase[i-1].toString()
        if (letter.lowercase() in display.lettersGuessed) {
            print(letter)
            print(" ")
        } else if ( letter == " ") {
            print(" ")
            print(" ")
        } else {
            ++counter
            print("_")
            print(" ")
        }
    }
    // If the player guesses all the letters then println()
    // Otherwise, update Document.missedLetters count.
    if (counter == 0) {
        println()
    } else {
        println()
        display.missedLetters = counter
    }
    // Display guessed letters and number of missed guesses left.
    print("Letters you've guessed already: ")
    for (i in 1..display.lettersGuessed.count()) {
        print("${display.lettersGuessed[i-1]} ")
    }
    println()
    println("You have ${display.maxGuesses-display.incorrectGuesses} missed guesses left.")
    return counter
}

//Function to handle a correct guess from the player.
//It returns a number of missed letters upon completion.
fun correctGuess(display: Display, guess: String): Int {
    if (guess.lowercase() in display.lettersGuessed) {
        println("That's a correct guess, but you already guessed it. Try a different letter")
    } else {
        display.lettersGuessed.add(guess.lowercase())
    }
    return createDisplay(display)
}

//Function to handle an incorrect guess from the player.
//It returns a number of missed letters upon completion.
fun incorrectGuess(display: Display, guess: String): Int {
    if (guess in display.lettersGuessed) {
        println("That's an incorrect guess and you already guessed it. Try a different letter")
    } else {
        display.lettersGuessed.add(guess)
        ++display.incorrectGuesses
    }
    return createDisplay(display)
}