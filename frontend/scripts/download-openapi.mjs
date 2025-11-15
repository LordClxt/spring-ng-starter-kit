import fetch from 'node-fetch'
import fs from 'fs/promises'
import {configDotenv} from 'dotenv'
configDotenv()
async function downloadOpenApiSpec() {
  try {
    const response = await fetch(`${process.env.BACKEND_URL}/v3/api-docs.yaml`)
    const data = await response.text()
    await fs.writeFile('openapi-specs/openapi.yaml', data)
    console.log('Spécification OpenAPI téléchargée avec succès.')
  } catch (e) {
    console.error('Erreur:', e.message)
  }
}

downloadOpenApiSpec()