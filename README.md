# ChatBot_Caelius

![ChatBot](icon2.png)

## Overview

This Java application is a chatbot with a graphical user interface (GUI) that connects to a MySQL database to provide responses to user input. It's a simple but functional chatbot that can answer questions or provide information based on the database.

## Features

- User-friendly GUI for chat interactions.
- Responses fetched from a MySQL database.
- The chatbot can handle common queries and provide informative responses.

## Requirements

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J library (for JDBC)

## Setup

1. **Database Setup**:
   - Make sure you have a MySQL server set up.
   - Create a database for the chatbot and configure the connection details in `ChatDatabase.initializeDatabase()`.

2. **Run the Application**:
   - Compile and run the `ChatBotJava` class.
   - The application GUI will open, and you can start interacting with the chatbot.

## Usage

- Type your message in the text field and click "SEND."
- The chatbot will respond with a message fetched from the database.
- User messages and bot responses are displayed in the chat window.

## Customization

- You can customize the appearance of the GUI, including fonts, colors, and dimensions, by modifying the code in the `ChatBotJava` class.
