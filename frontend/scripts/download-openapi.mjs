import fetch from 'node-fetch'
import fs from 'fs/promises'

async function downloadOpenApiSpec() {
  try {
    const response = await fetch('http://localhost:8080/v3/api-docs.yaml')
    const data = await response.text()
    await fs.writeFile('openapi-specs/openapi.yaml', data)
    console.log('Spécification OpenAPI téléchargée avec succès.')
  } catch (e) {
    console.error('Erreur:', e.message)
  }
}

downloadOpenApiSpec()