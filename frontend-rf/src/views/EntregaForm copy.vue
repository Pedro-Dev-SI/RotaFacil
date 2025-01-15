<template>
    <div>
        <div class="center-box">
            <div class="box">
                <div>
                    <h2 class="form-title">Dados da entrega</h2>
                    <div class="accordion" role="tablist">
                    </div>
                    <b-form @submit="onSubmit" @reset="onReset" v-if="show" class="form-container">
                        <b-form-group
                        id="input-group-1"
                        label="Nome do cliente:"
                        label-for="input-1"
                        class="custom-label-group"
                        >
                            <b-form-input
                                id="input-1"
                                v-model="form.email"
                                type="email"
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
                                    v-model="form.email"
                                    type="email"
                                    placeholder="CEP"
                                    required
                                    class="custom-input"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-group
                                id="input-group-3"
                                label="Rua"
                                label-for="input-3"
                                class="custom-label-group"
                                style="width: 100%"
                            >
                                <b-form-input
                                    id="input-3"
                                    v-model="form.email"
                                    type="email"
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
                                    v-model="form.email"
                                    type="email"
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
                                    v-model="form.email"
                                    type="email"
                                    placeholder="Bairro"
                                    required
                                    class="custom-input"
                                ></b-form-input>
                            </b-form-group>
                        </div>

                        <b-form-group id="input-group-6" class="custom-label-group">
                            <b-form-checkbox
                            id="checkbox-1"
                            v-model="status"
                            name="checkbox-1"
                            value="accepted"
                            unchecked-value="not_accepted"
                            >
                                Possui perecíveis? (Sim)
                            </b-form-checkbox>
                        </b-form-group>

                        <b-form-group id="input-group-7" label="Observações" label-for="textarea-1" class="custom-label-group">
                            <b-form-textarea
                                id="textarea-1"
                                v-model="text"
                                placeholder="Informe uma observação opcional..."
                                rows="3"
                                max-rows="6"
                            ></b-form-textarea>
                        </b-form-group>

                        <div class="group-buttons">
                            <b-button class="custom-buttons submit-btn" type="submit" variant="primary">Submit</b-button>
                            <b-button class="custom-buttons reset-btn" type="reset" variant="danger">Reset</b-button>
                        </div>

                    </b-form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name: "EntregaForm",
  data() {
    return {
      form: {
        email: '',
        name: '',
        food: null,
        checked: []
      },
      foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
      show: true
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      this.form.email = '';
      this.form.name = '';
      this.form.food = null;
      this.form.checked = [];
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
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
  font-size: 30px;
  font-weight: bold;
}

.center-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.box {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-top: 90px;
  width: 45%;
  min-height: 600px;
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
        width: 250px;
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

</style>
