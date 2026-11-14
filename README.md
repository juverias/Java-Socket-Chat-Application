# Java Socket Chat Application

A Java Swing-based client-server chat application that enables real-time communication between two systems using Java Socket Programming.

## Features

- Real-time messaging between client and server.
- Java Swing graphical user interface.
- Socket-based client-server communication.
- IP address-based client connection.
- Audio notification when a new message is received.
- MP3 sound integration using JLayer.

## Technologies Used

- Java 21
- Java Swing (GUI)
- Java Socket Programming
- TCP/IP
- DataInputStream and DataOutputStream
- JLayer (MP3 audio playback)
- Eclipse IDE

## Project Structure

```text
Java-Socket-Chat-Application/
│
├── ChatAppClient/
│   ├── src/
│   │   ├── client/
│   │   │   ├── Client.java
│   │   │   └── ClientMain.java
│   │   └── sound/
│   │       └── chat_sound.mp3
│   │
│   ├── .classpath
│   └── .project
│
├── ChatAppServer/
│   ├── src/
│   │   ├── server/
│   │   │   ├── Server.java
│   │   │   └── ServerMain.java
│   │   └── sound/
│   │       └── chat_sound.mp3
│   │
│   ├── .classpath
│   └── .project
│
└── README.md
```

## How to Run

1. Clone or download this repository.
2. Import `ChatAppServer` and `ChatAppClient` as separate existing projects into Eclipse.
3. Add the JLayer library to the project's build path.
4. Run `ServerMain.java` from the server project first.
5. Run `ClientMain.java` from the client project.
6. Enter the server's IP address in the client application.
7. Start chatting!

**Note:** Both applications must be able to communicate over the network. The server must be running before the client connects.

## Author

**Juveria Shaikh**

GitHub: [juverias](https://github.com/juverias)
