// Inserção 1
db.denuncia.insertOne({
  id_denuncia: 1,
  titulo: 'Título 1',
  descricao: 'Descrição 1',
  data_hora: new Date(),
  status_denuncia: 1,
  categoria: 1,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 123
});

// Inserção 2
db.denuncia.insertOne({
  id_denuncia: 2,
  titulo: 'Título 2',
  descricao: 'Descrição 2',
  data_hora: new Date(),
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 456
});

// Inserção 3
db.denuncia.insertOne({
  id_denuncia: 3,
  titulo: 'Título 3',
  descricao: 'Descrição 3',
  data_hora: new Date(),
  status_denuncia: 1,
  categoria: 3,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 789
});

// Inserção 4
db.denuncia.insertOne({
  id_denuncia: 4,
  titulo: 'Título 4',
  descricao: 'Descrição 4',
  data_hora: new Date(),
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 123
});

// Busca 1: Todas as denúncias
db.denuncia.find();

// Busca 2: Denúncias com status 1
db.denuncia.find({ status_denuncia: 1 });

// Busca 3: Denúncias da categoria 2
db.denuncia.find({ categoria: 2 });

// Busca 4: Denúncias do usuário com ID 123
db.denuncia.find({ id_usuario: 123 });