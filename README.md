# Monster Trading Cards Game (MTCG)

## Overview
This project is about developing an HTTP/REST-based server that facilitates a trading and battling platform for a magical card game. The server is designed to support user registration, card management, and battles, with a PostgreSQL database for persistent data storage.

### Features
- **User Registration & Management:** Allows users to register, log in, and manage their profiles.
- **Card Management:** Users can buy cards, manage their collections, and assemble decks for battling.
- **Trading System:** Enables users to trade cards to strategically enhance their decks.
- **Battle System:** Users can engage in battles using their decks.
- **Scoreboard & Stats:** Tracks and displays user statistics and rankings.

## Game Mechanics

### User Capabilities
- Registering and logging into the server.
- Acquiring cards to create decks.
- Battling other users with their decks.
- Trading cards for better deck composition.
- Viewing statistics and rankings on a scoreboard.

### Cards
- **Types:** Includes Spell cards and Monster cards.
- **Attributes:** Each card has a name, damage, and an element type.

### Battles
- Battles are determined by the cards' element types and specific rules for interactions between card types.

## Technical Specifications

### Persistence
- The application uses a PostgreSQL database for storing all data.

### Security
- Implements server-side token verification to ensure actions are authorized for the correct user.

## Development Requirements

- The server must be implemented in Java without using frameworks for HTTP communication.
- Serialization/deserialization of objects into/from strings is handled using the Jackson library.
- The application includes unit and integration tests to ensure functionality.

## Submission Instructions
- Submit the source code as a ZIP file, including a README.md or .txt file that links to your Git repository.
- Provide a protocol document that details the technical steps taken, rationale behind unit tests chosen, and time spent on the project.

## Optional Features
- Implementation of optional features can offset potential deficiencies in meeting the mandatory requirements.

## Grading Criteria
- Functional requirements (30%)
- Non-functional requirements (20%)
- Documentation and protocol (5%)


