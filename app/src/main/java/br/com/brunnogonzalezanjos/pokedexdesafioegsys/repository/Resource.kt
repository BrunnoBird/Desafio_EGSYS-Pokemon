package br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository

open class Resource<T>(
    val data: T?,
    val error: String? = null
)

fun <T> createResourceFail(
    actualResource: Resource<T>?,
    erro: String?
): Resource<T> {
    if (actualResource != null) {
        return Resource(data = actualResource.data, error = erro)
    }
    return Resource(data = null, error = erro)
}