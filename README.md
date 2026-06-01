# pet-adoption-shelter
Pet Adoption Shelter - CAS-SSDD-26-1 Exercise

The Pet Adoption Shelter is a secure web application that connects potential adopters with shelter animals. The platform allows users to dynamically search for pets, engage with a community blog, and submit secure adoption applications, while providing shelter staff and administrators with the tools needed to manage inventory, moderate content, and safeguard sensitive user data.

Core System Features:

Architecture: RESTful API backend serving JSON data.

Access Control: Secure login with two distinct roles (Adopter, Shelter Staff).

Data Security: Encrypted storage of secret user data, including passwords and background checks.

Media & Input: File upload capabilities for identity verification and pet photos, alongside a public blog for community comments.

Search: Input-dependent database queries for dynamic pet filtering.

## Main use cases
* Registration of a shelter
* Shelter registers a pet 
* Shelter adds pictures of the pet and writes comments
* Registration of an adopter
* Adopter searches for pets
* Adopter creates an adoption request uploading his id-card
* Shelter approves the adoption request

## Key decisions
The applications is written according to the hexagonal architecture and implements strong input validation.
The classes belonging to the model are annotated with custom-made annotations, in order to document the key domain-concepts and all the attributes of aggregate roots and value objects.
The two types of users are modeled as two different JPA entities because they have distinct use cases that don't overlap.   