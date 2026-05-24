# =========================
# Build Stage
# =========================
FROM node:22.12.0-alpine AS build
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm ci

# Copy the rest of the application and build
COPY . .
RUN npm run build --configuration=production

# =========================
# Production Stage (Nginx)
# =========================
FROM nginx:alpine AS prod

# Copy the built Angular app from the build stage to Nginx's default public directory
# Note: In Angular 17+, the build output is placed in dist/<project-name>/browser
# Adjust 'pet-adoption-ui' if your angular.json project name is different
COPY --from=build /app/dist/pet-adoption-ui/browser /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
