# 📡 Lab 02 Paradigmas de Prgramacion

#**Grupo**
2-dp
A command-line application for fetching and displaying RSS and Reddit feeds, implemented in Java using object-oriented principles.

## ✨ Features

- 📥 Parses subscription details from a JSON file.
- 🔗 Constructs feed URLs dynamically based on provided parameters.
- 🌐 Fetches and displays feed content using Java's `HttpClient`.
## Proyect Structure
  ```css
  java-feed-reader/
├── pom.xml
└── src/
    └── main/
        └── java/
            ├── Main.java
            ├── feed/
            ├── parser/
            ├── subscription/
            └── httpRequest/
```

## 🚀 Getting Started

### Prerequisites

- ☕ Java 11 or higher
- 🛠️ Maven 3.6 or higher

### Installation

1. Clone the repository:

   ```bash
   GH CLI:
   gh repo clone Paradigmas25-g40/lab02
   cd java-feed-reader
   HTTPS:
   git clone https://github.com/Paradigmas25-g40/lab02/edit/main/README.md
   cd java-feed-reader
2. Build
   ```bash
    mvn compile
    mvn exec:java
###📝 Usage
Create a subscriptions.json file in the project root with the following structure:

```json
[
  {
    "url": "https://rss.nytimes.com/services/xml/rss/nyt/%s.xml",
    "params": ["Technology", "Science"],
    "type": "rss"
  },
  {
    "url": "https://www.reddit.com/r/java/.json",
    "type": "reddit"
  }
]

```
The application will read this file, construct the appropriate URLs, and fetch the feed content.
