-- Criando a tabela "rota"
CREATE TABLE public.rota (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(255) NOT NULL
);

-- Criando a tabela "entrega"
CREATE TABLE public.entrega (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(255) NOT NULL,
    nome_cliente VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    pereciveis BOOLEAN NOT NULL,
    observacoes TEXT,
    status VARCHAR(50), -- Usando STRING para o Enum, você pode ajustar conforme seu Enum StatusEntregaEnum
    coordenadas_entrega geometry(Point, 4326) NOT NULL, -- Tipo geográfico
    rota_id UUID NOT NULL,
    CONSTRAINT fk_rota FOREIGN KEY (rota_id) REFERENCES public.rota(id) ON DELETE CASCADE
);

-- Criando a extensão do PostGIS, caso não tenha sido criada
CREATE EXTENSION IF NOT EXISTS postgis;
