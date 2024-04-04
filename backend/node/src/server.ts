import fastify from "fastify";

const app = fastify();

app.get('/', () => { return 'Hello World Unite' })
app.get('/teste', () => { return 'Hello World Teste' })

app.listen({ port: 3333 }).then(() => {
  console.log('HTTP server running!');
});