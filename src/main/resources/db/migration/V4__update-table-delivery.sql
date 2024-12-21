-- Adicionar o campo 'main' à tabela deliverys
ALTER TABLE deliverys
ADD main BOOLEAN DEFAULT FALSE;

-- Atualizar todos os registros existentes para definir o campo 'main' como FALSE
UPDATE deliverys
SET main = FALSE;
