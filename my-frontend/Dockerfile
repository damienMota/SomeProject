# Use Node.js as the base image
FROM node:20.11.1

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your frontend code into the container
COPY . .

# Build the frontend
RUN npm run build

# Expose the frontend port (typically 3000 for React)
EXPOSE 3000

# Start the frontend service
CMD ["npm", "start"]
