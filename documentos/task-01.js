use vem_ser
switched to db vem_ser
db.createCollection("denuncia")
{ ok: 1 }

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
{
    acknowledged: true,
    insertedId: ObjectId("65d3f7d4835a13730a899628")
}

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

{
   acknowledged: true,
   insertedId: ObjectId("65d3f8a1835a13730a899629")
}

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

{
    acknowledged: true,
    insertedId: ObjectId("65d3f8cc835a13730a89962a")
}

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

{
    acknowledged: true,
    insertedId: ObjectId("65d3f917835a13730a89962b")
}

// Busca 1: Todas as denúncias
db.denuncia.find();

{
  _id: ObjectId("65d3f7d4835a13730a899628"),
  id_denuncia: 1,
  titulo: 'Título 1',
  descricao: 'Descrição 1',
  data_hora: 2024-02-20T00:52:36.988Z,
  status_denuncia: 1,
  categoria: 1,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 123
}

{
  _id: ObjectId("65d3f8a1835a13730a899629"),
  id_denuncia: 2,
  titulo: 'Título 2',
  descricao: 'Descrição 2',
  data_hora: 2024-02-20T00:56:01.524Z,
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 456
}

{
  _id: ObjectId("65d3f8cc835a13730a89962a"),
  id_denuncia: 3,
  titulo: 'Título 3',
  descricao: 'Descrição 3',
  data_hora: 2024-02-20T00:56:44.990Z,
  status_denuncia: 1,
  categoria: 3,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 789
}

{
  _id: ObjectId("65d3f917835a13730a89962b"),
  id_denuncia: 4,
  titulo: 'Título 4',
  descricao: 'Descrição 4',
  data_hora: 2024-02-20T00:57:59.443Z,
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 123
}

// Busca 2: Denúncias com status 1
db.denuncia.find({ status_denuncia: 1 });

{
  _id: ObjectId("65d3f7d4835a13730a899628"),
  id_denuncia: 1,
  titulo: 'Título 1',
  descricao: 'Descrição 1',
  data_hora: 2024-02-20T00:52:36.988Z,
  status_denuncia: 1,
  categoria: 1,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 123
}

{
  _id: ObjectId("65d3f8cc835a13730a89962a"),
  id_denuncia: 3,
  titulo: 'Título 3',
  descricao: 'Descrição 3',
  data_hora: 2024-02-20T00:56:44.990Z,
  status_denuncia: 1,
  categoria: 3,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 789
}

// Busca 3: Denúncias da categoria 2
db.denuncia.find({ categoria: 2 });

{
  _id: ObjectId("65d3f8a1835a13730a899629"),
  id_denuncia: 2,
  titulo: 'Título 2',
  descricao: 'Descrição 2',
  data_hora: 2024-02-20T00:56:01.524Z,
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 456
}

{
  _id: ObjectId("65d3f917835a13730a89962b"),
  id_denuncia: 4,
  titulo: 'Título 4',
  descricao: 'Descrição 4',
  data_hora: 2024-02-20T00:57:59.443Z,
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 123
}

// Busca 4: Denúncias do usuário com ID 123
db.denuncia.find({ id_usuario: 123 });

{
  _id: ObjectId("65d3f7d4835a13730a899628"),
  id_denuncia: 1,
  titulo: 'Título 1',
  descricao: 'Descrição 1',
  data_hora: 2024-02-20T00:52:36.988Z,
  status_denuncia: 1,
  categoria: 1,
  tipo_denuncia: 1,
  numero_curtidas: 0,
  id_usuario: 123
}

{
  _id: ObjectId("65d3f917835a13730a89962b"),
  id_denuncia: 4,
  titulo: 'Título 4',
  descricao: 'Descrição 4',
  data_hora: 2024-02-20T00:57:59.443Z,
  status_denuncia: 2,
  categoria: 2,
  tipo_denuncia: 2,
  numero_curtidas: 0,
  id_usuario: 123
}