#!/bin/bash

# Get the directory path of the script
script_dir=$(dirname "$0")

# Define the endpoint URL
endpoint_url="http://my-alb-test-110943693.us-east-1.elb.amazonaws.com/hello"

# Define the number of requests
num_requests=500

# Define the log file with a timestamp
log_file="$script_dir/curl_logs_$(date +'%Y%m%d%H%M%S').log"

# Loop to send requests
for ((i=1; i<=$num_requests; i++))
do
    # Generate a timestamp
    timestamp=$(date +'%Y-%m-%d %H:%M:%S')

    # Send a GET request using curl and log the result with a timestamp
    response=$(curl -s $endpoint_url)

    # Append the timestamp and response to the log file
    echo "$timestamp - Response: $response" >> $log_file
done

echo "All requests completed. Log saved to $log_file."
