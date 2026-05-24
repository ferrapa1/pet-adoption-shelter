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
# When SSR is disabled and outputMode is removed, the build output is placed directly in dist/<project-name>/browser
COPY --from=build /app/dist/pet-adoption-ui/browser /usr/share/nginx/html

# Copy the custom nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
