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

# Copy the built Angular app from the build stage
COPY --from=build /app/dist/pet-adoption-ui/browser /usr/share/nginx/html

# Copy the Nginx configuration template.
# This will be processed by the entrypoint script to substitute environment variables.
COPY default.conf.template /etc/nginx/templates/default.conf.template

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
