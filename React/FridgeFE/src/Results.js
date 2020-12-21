import React from 'react';
import { Header } from 'semantic-ui-react'

class Results extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            ingredients: ['양파', '버섯', '파', '김치'],
        };
    }

    componentDidMount() {
        const ingredients = this.state.ingredients
        const ingredientsString = ingredients.join(',')
        const url = 'http://localhost:9000/?query='.concat(ingredientsString)

        fetch(url)
           .then((result) => result.json())
           .then(response => {
            console.log(response)
          })
          .catch(err => {
            console.log(err);
          });
          
    }

    render() {
       const ingredients = this.state.ingredients
      return (
          <div>
              <Header as='h3'> Recipes </Header>
              {ingredients.map((i) =>(
                  <p>{i}</p>
              ))}
          </div>
      )
    }
}


export default Results;
