#!/usr/bin/env bash
set -euo pipefail

if [[ $# -ne 1 ]]; then
  echo "usage: $0 <task name>" >&2
  exit 1
fi

task="$1"
source_file="$(find . -name "$task.java" -type f -print -quit)"

if [[ -z "$source_file" ]]; then
  echo "task not found: $task.java" >&2
  exit 1
fi

task_dir="$(dirname "$source_file")"
build_dir="$(mktemp -d)"
trap 'rm -rf "$build_dir"' EXIT

javac -d "$build_dir" "$source_file"

inputs=( "$task_dir"/"$task".in* )
if [[ ! -e "${inputs[0]}" ]]; then
  echo "no input files found matching $task_dir/$task.in*" >&2
  exit 1
fi

for input in "${inputs[@]}"; do
  echo "=== input: $input ==="
  time java -cp "$build_dir" "$task" < "$input"
  echo
done
