#!/bin/bash

# Compilation script for Catan Simulator

echo "Compiling Catan Simulator..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin src/main/java/catan/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Run with: ./run.sh"
else
    echo "✗ Compilation failed!"
    exit 1
fi
