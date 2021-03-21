import React from 'react';
import { useForm } from "react-hook-form";

function AddingIngredient() {

    const {register, handleSubmit, errors} = useForm();

    const onSubmit = (data) => {
        console.log(data)
    }

    return (
        <form className='addingIngredient' onSubmit={handleSubmit(onSubmit)}>
            <h1>Lägga till ingrediens!</h1>

            <input type="text" placeholder="Skriv in namn" name="name" ref={register({ required: "Namn på ingrediens krävs"})}/>
            <input type="text" placeholder="Skriv in inköpspris" name="purchasePrice" ref={register}/>
            <input type="text" placeholder="Skriv in vikt el antal" name="purchaseOrWeightQuantity" ref={register}/>
            <input type="text" placeholder="Skriv in 1dl vikt" name="oneDeciliterWeight" ref={register}/>
            <input type="submit" />

            {errors.name && <p>{errors.name.message}</p>}
        </form>
    );
}

export default AddingIngredient;