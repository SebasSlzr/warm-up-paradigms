const fs = require("fs");
const readline = require("readline");

const file = "books.json";

function loadBooks() {
    if (fs.existsSync(file)) {
        return JSON.parse(fs.readFileSync(file));
    } else {
        return [];
    }
}

function saveBooks(books) {
    fs.writeFileSync(file, JSON.stringify(books, null, 2));
}

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function menu() {
    console.log("\n1. Add book");
    console.log("2. List books");
    console.log("3. Search by title");
    console.log("4. Remove by title");
    console.log("5. Exit");

    rl.question("Option: ", ans => {
        let books = loadBooks();

        if (ans === "1") {
            rl.question("Title: ", t => {
                rl.question("Author: ", a => {
                    rl.question("Year: ", y => {
                        rl.question("Genre: ", g => {
                            books.push({ title: t, author: a, year: y, genre: g });
                            saveBooks(books);
                            console.log("Book added");
                            menu();
                        });
                    });
                });
            });
        } else if (ans === "2") {
            if (books.length === 0) console.log("No books");
            else books.forEach(b => console.log(b.title + " - " + b.author));
            menu();
        } else if (ans === "3") {
            rl.question("Search title: ", t => {
                let found = books.filter(b => b.title.toLowerCase().includes(t.toLowerCase()));
                if (found.length === 0) console.log("Not found");
                else found.forEach(b => console.log(b.title + " - " + b.author));
                menu();
            });
        } else if (ans === "4") {
            rl.question("Title to remove: ", t => {
                let newBooks = books.filter(b => b.title.toLowerCase() !== t.toLowerCase());
                saveBooks(newBooks);
                console.log("Book removed (if existed)");
                menu();
            });
        } else if (ans === "5") {
            rl.close();
        } else {
            console.log("Invalid option");
            menu();
        }
    });
}

menu();
