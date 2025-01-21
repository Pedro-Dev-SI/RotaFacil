<template>
  <div>
    <div class="center-box">
      <div v-for="(entrega, index) in listaEntregas" :key="index" class="box">
        <div>
          <h2 class="form-title" @click="toggleCollapse(index)">
            {{ `Entrega ${index + 1} - ${entrega.form.nomeCliente}` }}
          </h2>
          <b-collapse v-model="entrega.isCollapsed" class="accordion custom-collapse" role="tablist">
            <b-form @submit="onSubmit(index)" @reset="onReset(index)" v-if="entrega.show" class="form-container">
              <b-form-group
                id="input-group-1"
                label="Nome do cliente:"
                label-for="input-1"
                class="custom-label-group"
              >
                <b-form-input
                  id="input-1"
                  v-model="entrega.form.nomeCliente"
                  type="text"
                  placeholder="Nome completo"
                  required
                  class="custom-input"
                ></b-form-input>
              </b-form-group>

              <div class="row-inputs">
                <b-form-group
                  id="input-group-2"
                  label="CEP"
                  label-for="input-2"
                  class="custom-label-group"
                  style="margin-right: 25px;"
                >
                  <b-form-input
                    id="input-2"
                    v-model="entrega.form.cep"
                    type="text"
                    placeholder="CEP"
                    required
                    class="custom-input"
                    @input="validaCep(index)"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="input-group-3"
                  label="Logradouro"
                  label-for="input-3"
                  class="custom-label-group"
                  style="width: 100%"
                >
                  <b-form-input
                    id="input-3"
                    v-model="entrega.form.logradouro"
                    type="text"
                    placeholder="Rua"
                    required
                    class="custom-input"
                  ></b-form-input>
                </b-form-group>
              </div>

              <div class="row-inputs">
                <b-form-group
                  id="input-group-4"
                  label="Número"
                  label-for="input-4"
                  class="custom-label-group"
                  style="margin-right: 25px;"
                >
                  <b-form-input
                    id="input-4"
                    v-model="entrega.form.numero"
                    type="text"
                    placeholder="Número"
                    required
                    class="custom-input"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="input-group-5"
                  label="Bairro"
                  label-for="input-5"
                  class="custom-label-group"
                  style="width: 100%"
                >
                  <b-form-input
                    id="input-5"
                    v-model="entrega.form.bairro"
                    type="text"
                    placeholder="Bairro"
                    required
                    class="custom-input"
                  ></b-form-input>
                </b-form-group>
              </div>

              <div class="row-inputs">
                <b-form-group
                  id="input-group-4"
                  label="UF"
                  label-for="input-4"
                  class="custom-label-group"
                  style="margin-right: 25px;"
                >
                  <b-form-input
                    id="input-4"
                    v-model="entrega.form.uf"
                    type="text"
                    placeholder="UF"
                    required
                    class="custom-input"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="input-group-5"
                  label="Cidade"
                  label-for="input-5"
                  class="custom-label-group"
                  style="width: 100%"
                >
                  <b-form-input
                    id="input-5"
                    v-model="entrega.form.cidade"
                    type="text"
                    placeholder="Cidade"
                    required
                    class="custom-input"
                  ></b-form-input>
                </b-form-group>
              </div>

              <b-form-group id="input-group-6" class="custom-label-group">
                <b-form-checkbox
                  id="checkbox-1"
                  v-model="entrega.form.pereciveis"
                  name="checkbox-1"
                  value=true
                  unchecked-value=false
                >
                  Possui perecíveis? (Sim)
                </b-form-checkbox>
              </b-form-group>

              <b-form-group
                id="input-group-7"
                label="Observações"
                label-for="textarea-1"
                class="custom-label-group"
              >
                <b-form-textarea
                  id="textarea-1"
                  v-model="entrega.form.observacoes"
                  placeholder="Informe uma observação opcional..."
                  rows="3"
                  max-rows="6"
                ></b-form-textarea>
              </b-form-group>
            </b-form>
          </b-collapse>
        </div>
      </div>

      <div class="group-buttons">
        
      </div>
    </div>

    <!-- Botão "Adicionar entrega" fixo -->
    <div class="btn-add-item">
      <b-button
        class="custom-buttons submit-btn"
        type="button"
        variant="primary"
        @click="adicionarEntrega"
        :disabled="listaEntregas.length >= 5"
      >
        Adicionar entrega +
      </b-button>

      <b-button
          class="custom-buttons submit-btn"
          type="submit"
          variant="success"
          style="margin-top: 10px;"
          :disabled='!entregasValidas'
          data-bs-toggle="modal"
          data-bs-target="#confirmModal"
        >
          Calcular rota
        </b-button>
    </div>
    <notifications position="top right" />
    <!-- Modal -->
    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="confirmModalLabel">Confirmar</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
          </div>
          <div class="modal-body">
            Deseja finalizar o cadastro de entregas ?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            <button type="button" class="btn btn-primary" @click="calcularRota" data-bs-dismiss="modal">Confirmar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import CepService from '@/services/cep.service.js';
import EntregaService from '@/services/entrega.service.js';


export default {
  name: "EntregaForm",
  components: {
  },
  data() {
    return {
      listaEntregas: [
        { 
          form: {
            nomeCliente: '',
            cep: '',
            logradouro: '',
            numero: '',
            bairro: '',
            uf: '',
            cidade: '',
            estado: '',
            pereciveis: false,
            observacoes: ''
          },
          isCollapsed: false,
          show: true
        }
      ],
      listaEntregasToSave: [],
      codigoRotaSalva: '',
    };
  },
  computed: {
    entregasValidas() {
      for (let entrega of this.listaEntregas) {
        if (entrega.form.nomeCliente === '' || entrega.form.cep === '' || entrega.form.logradouro === '' || entrega.form.numero === '' || entrega.form.bairro === '' || entrega.form.uf === '' || entrega.form.cidade === '') {
            return false;
        }
      }
      return true;
    }
  },
  methods: {
    onSubmit(index, evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.listaEntregas[index].form));
    },
    onReset(index, evt) {
      evt.preventDefault();
      this.listaEntregas[index].form = {
        nomeCliente: '',
        cep: '',
        logradouro: '',
        numero: '',
        bairro: '',
        pereciveis: false,
        observacoes: '',
      };
    },
    toggleCollapse(index) {
      this.listaEntregas[index].isCollapsed = !this.listaEntregas[index].isCollapsed;
    },

    validarEntregasCadastradas() {
        for (let entrega of this.listaEntregas) {
            if (entrega.form.nomeCliente === '' || entrega.form.cep === '' || entrega.form.logradouro === '' || entrega.form.numero === '' || entrega.form.bairro === '' || entrega.form.uf === '' || entrega.form.cidade === '') {
                return false;
            }
        }
        return true;
    },

    adicionarEntrega() {
        
      const entregasValidas = this.validarEntregasCadastradas();

      if (this.listaEntregas.length < 5) {

        if (!entregasValidas) {
          this.$notify({
            title: 'Atenção',
            text: 'Preencha todos os campos da entrega antes de adicionar uma nova.',
            type: 'error'
          });
          return;
        }

        this.listaEntregas.push({
          form: {
          nomeCliente: '',
          cep: '',
          logradouro: '',
          numero: '',
          bairro: '',
          pereciveis: false,
          uf: '',
          cidade: '',
          estado: '',
          observacoes: ''
          },
          isCollapsed: true,
          show: true
        });

        this.listaEntregas[this.listaEntregas.length - 2].isCollapsed = false;
      }
    },
    async calcularRota() {

      this.listaEntregas.forEach(async (l) => {
        this.listaEntregasToSave.push(l.form);
      })

      console.log(this.listaEntregasToSave);

      try {
        const { data } = await EntregaService.createEntrega(this.listaEntregasToSave);

        this.codigoRotaSalva = data[0].codigoRota

        this.$router.push({ name: 'ResultadoCriacao', params: { codigoRota: this.codigoRotaSalva } });

      } catch (error) {
        console.log(error);
      }

    },
    async validaCep(index) {
      if (this.listaEntregas[index].form.cep.length === 8) {
          try {
              var responseCep = await CepService.getCepData(this.listaEntregas[index].form.cep);
              this.listaEntregas[index].form.logradouro = responseCep.logradouro;
              this.listaEntregas[index].form.bairro = responseCep.bairro;
              this.listaEntregas[index].form.uf = responseCep.uf;
              this.listaEntregas[index].form.cidade = responseCep.localidade;
              this.listaEntregas[index].form.estado = responseCep.estado;
              console.log(responseCep);
          } catch (error) {
              this.$notify({
                  title: 'Atenção',
                  text: 'CEP inválido.',
                  type: 'error'
              });
          }
      }
    }
  }
}
</script>

<style lang="scss">
.form-container {
  margin: 0 auto;
  width: 95%;
}

.custom-label-group {
  color: white;
  font-size: 18px;
  font-weight: bold;
}

.custom-input {
  height: 50px;
}

.form-title {
  text-align: left;
  margin-bottom: 30px;
  margin-top: 30px;
  margin-left: 30px;
  color: white;
  font-size: 25px;
  font-weight: bold;
  cursor: pointer; /* Torna o título clicável */
}

.center-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  flex-direction: column;
}

.column-div {
  display: flex;
  flex-direction: column;
}

.box {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-top: 15px;
  width: 45%;
  min-height: 40px;
  background-color: #5ac8fa;
  border-radius: 20px;
}

.row-inputs {
  display: flex;
  width: 100%;
}

.group-buttons {
  display: flex;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 40px;
  justify-content: space-around;

  .custom-buttons {
    width: 500px;
    height: 50px;
    font-size: 20px;
    font-weight: bold;
  }

  .submit-btn {
    background-color: #32BC9B;
    border: none;
  }

  .reset-btn {
    background-color: #979797;
    border: none;
  }
}

/* Animação para o Collapse */
.custom-collapse {
  transition: all 0.2s ease;
}

/* Quando o collapse está aberto, vamos animar a altura */
.custom-collapse .collapse {
  max-height: 1000px; /* Suficiente para o conteúdo expandido */
  opacity: 1;
  transition: max-height 0.3s ease-in-out, opacity 0.3s ease-in-out;
}

.custom-collapse .collapse:not(.show) {
  max-height: 0;
  opacity: 0;
}

/* Botão Adicionar Item fixo à direita */
.btn-add-item {
    display: flex;
    flex-direction: column;
    position: fixed;
    bottom: 30px;
    right: 30px;
    z-index: 1000;
}

</style>
