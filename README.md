# pet-adoption-shelter
Pet Adoption Shelter - CAS-SSDD-26-1 Exercise

This is an example application for the CAS-SSDD-26-1 course. 
The Pet Adoption Shelter is a web portal where people can adopt pets. Volunteers upload animals pictures and adopters can search and request pets for adoption.
In a nutshell, the project has folloqing requirements:

 - API: A backend service that provides JSON data for available pets, adoption applications, and user profiles.
 - Login functionality: A secure portal for potential pet adopters, shelter volunteers, and system administrators to access their accounts.

- At least two distinct Roles:
 -- Adopter (Standard User): Can browse pets, submit adoption applications, and save favourites.
 -- Shelter Staff: Can add new pets, approve/reject applications, and manage blog posts.
 -- Administrators: can review comments, check/block users, generally acts as a super-user
  
 - Secret user data: Encrypted passwords, sensitive background check information for adoptions, home addresses.
 - File upload: Adopters upload proof of identity or home photos (to check for pet safety). Staff upload pet profile pictures.
 - Blog / Comment functionality: A "Shelter News & Success Stories" blog where staff post updates and users leave comments or questions about specific pets.
 - Input-dependent database queries: A dynamic search and filter bar where users type keywords (e.g., "Golden Retriever", "friendly with cats", "young") to query the pet database

