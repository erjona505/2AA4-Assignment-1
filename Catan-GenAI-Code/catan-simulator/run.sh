#!/bin/bash

# Execution script for Catan Simulator

# Check if compiled
if [ ! -d "bin" ] || [ -z "$(ls -A bin)" ]; then
    echo "Project not compiled. Running compilation first..."
    ./compile.sh
fi

# Run the demonstrator
echo ""
echo "Running Catan Simulator..."
echo ""

java -cp bin catan.Demonstrator
