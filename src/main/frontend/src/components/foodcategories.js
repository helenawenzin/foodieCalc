 import React from 'react'

    const FoodCategories = ({ foodCategories }) => {
      return (
        <div>
          <center><h1>FoodCategory List</h1></center>
          {foodCategories.map((foodCategory) => (
            <div className="card" key="{foodCategory.id}">
              <div className="card-body">
                <h5 className="card-title">{foodCategory.name}</h5>
                <p className="card-text">{foodCategory.id}</p>
              </div>
            </div>
          ))}
        </div>
      )
    };

    export default FoodCategories