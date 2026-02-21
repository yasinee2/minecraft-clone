#Vibecoded btw
import os
from pathlib import Path

# Directory containing the images
texture_dir = r"src\main\resources\textures\item"

# Get all PNG files in the directory
for filename in os.listdir(texture_dir):
    if filename.endswith(".png"):
        old_path = os.path.join(texture_dir, filename)
        # Split filename and extension
        name, ext = os.path.splitext(filename)
        new_filename = f"{name}_item{ext}"
        new_path = os.path.join(texture_dir, new_filename)
        # Rename the file
        os.rename(old_path, new_path)
        print(f"Renamed: {filename} -> {new_filename}")

print("Done!")