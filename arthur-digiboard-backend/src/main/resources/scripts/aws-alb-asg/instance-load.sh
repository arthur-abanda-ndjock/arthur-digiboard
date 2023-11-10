#!/bin/bash

# Get the directory path of the script
script_dir=$(dirname "$0")


# Define the number of concurrent instances
num_concurrent_instances=10  # Adjust this number as needed

# Loop to start multiple instances
for ((i=1; i<=$num_concurrent_instances; i++))
do
    # Run the script in the background and store the process ID
    "$script_dir/ping-endpoint.sh" &

    # Optional: Sleep for a short duration before starting the next instance
    sleep 1
done

# Wait for all background jobs (instances) to complete
wait

echo "All instances completed."
